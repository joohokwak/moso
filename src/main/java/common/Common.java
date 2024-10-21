package common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.google.gson.Gson;

public class Common {
	// parameter to class
	public static <T> T convert(HttpServletRequest request, Class<T> clazz) {
        Map<String, Object> map = new HashMap<>();
        
        Enumeration<String> em = request.getParameterNames();
        
        // 요청된 파라미터 개수 만큼 반복
        while (em.hasMoreElements()) {
            // 파라미터명 얻기
            String key = em.nextElement();
            String[] values = request.getParameterValues(key);
            
            // 전달된 name 값이 여러 개인지 체크
            if (values.length > 1) {
                StringBuilder sb = new StringBuilder();
                for (String value : values) {
                    sb.append(value).append(", ");
                }
                sb.setLength(sb.length() - 2); // 마지막 ", " 제거
                map.put(key, sb.toString());
            } else {
                map.put(key, request.getParameter(key));
            }
        }
        
        if (!map.isEmpty()) {
            Gson gson = new Gson();
            // 맵 데이터를 원하는 객체 타입으로 변환
            return gson.fromJson(gson.toJson(map), clazz);
        }
        
        return null; // 빈 객체를 반환할 수도 있음
    }
	
	// json type data to map
	@SuppressWarnings("unchecked")
	public static Map<String, Object> jsonConvert(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<>();
		
		try {
			// json 형태로 전달된 데이터 받기
			BufferedReader br = request.getReader();
			String reader = br.readLine();
			
			// Gson 객체를 이용하여 map 데이터 변환
			Gson gson = new Gson();
			map = gson.fromJson(reader, map.getClass());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return map;
	}
	
	// json data로 응답하기
	public static void jsonResponse(HttpServletResponse response, Object obj) {
		// json data 로 응답하기 위한 설정
		response.setContentType("application/json; charset=utf-8");
		
		try (PrintWriter pw = response.getWriter();) {
			// Gson 객체를 이용해 json 형태로 변환
			Gson gson = new Gson();
			String resData = gson.toJson(obj);
			
			// 응답
			pw.write(resData);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 파일 업로드
	// 사용시 서블릿에 @MultipartConfig 추가 필요!!
	public static Map<String, String> fileUpload(HttpServletRequest request, String path) {
		String ATTACHED_DIR = request.getServletContext().getRealPath(path);
		
		Map<String, String> map = null;
		
		try {
			// 요청 타입 확인
			String contentType = request.getContentType();
			
			// multipart/form-data 로 요청되었는지 체크
			if (contentType != null && contentType.toLowerCase().startsWith("multipart/")) {
				// 전달된 데이터를 컬렉션으로 받기
				Collection<Part> parts = request.getParts();
				
				for (Part part : parts) {
					// 컬렉션 데이터가 파일인지 체크
					if (part.getHeader("Content-Disposition").contains("filename=")) {
						// 파일데이터가 있는지 체크
						if (part.getSize() > 0) {
							map = new HashMap<>();
							
							// 파일명을 얻기위에 메서드 호출
							String fileName = extractFileName(part.getHeader("Content-Disposition"));
							if (fileName != null) {
								// 확장자
								String ext = fileName.substring(fileName.lastIndexOf("."));
								// 현재 시간 (millisecond 단위)
								String now = new SimpleDateFormat("yyyyMMdd_HmsS").format(new Date());
								// 현재 시간 + 확장자로 새 파일명 생성
								String newFileName = now + ext;
								
								// 리턴하기 위해 맵에 origin file, new file 명 담기
								map.put("ofile", fileName);
								map.put("nfile", newFileName);
								
								// 디렉토리가 없으면 생성!!
								File file = new File(ATTACHED_DIR);
								if (!file.exists()) file.mkdir();
								
								// 설정경로에 파일 업로드
								part.write(ATTACHED_DIR + File.separator + newFileName);
								part.delete();
							}
						}
					}
				}
			}
		} catch (IOException | ServletException e) {
			e.printStackTrace();
		}
		
		return map;
	}
	
	// 파일명을 얻기위한 메서드
	private static String extractFileName(String partHeader) {
		for (String cd : partHeader.split(";")) {
			if (cd.trim().startsWith("filename")) {
				String fileName = cd.substring(cd.indexOf("=") + 1).trim().replace("\"", "");
				int idx = fileName.lastIndexOf(File.separator);
				return fileName.substring(idx + 1);
			}
		}
		
		return null;
	}

	// 파일 다운로드
	public static void fileDownLoad(HttpServletResponse response, String path, String saveFileName, String originFileName) {
		// 파일 경로 및 저장된 파일명을 file 객체로 얻기
		File file = new File(path, saveFileName);
		
		// 파일을 찾아 입력 스트림 생성
		try (InputStream inStream = new FileInputStream(file); OutputStream outStream = response.getOutputStream();) {

			// 한글 파일명 깨짐 방지
			originFileName = URLEncoder.encode(originFileName, "UTF-8").replaceAll("\\+", "%20");
			
			// 파일 다운로드용 응답 헤더 설정
			response.reset();
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment; filename=\"" + originFileName + "\"");
			response.setHeader("Content-Length", file.length() + "");

			// 출력 스트림을 이용해 다운로드 하기
			byte b[] = new byte[(int) file.length()];
			int readBuffer = 0;
			while ((readBuffer = inStream.read(b)) > 0) {
				outStream.write(b, 0, readBuffer);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 파일 삭제
	public static void fileDelete(HttpServletRequest request, String path, String fileName) {
		// 파일 경로
		String ATTACHED_DIR = request.getServletContext().getRealPath(path);
		// 파일 경로 및 저장된 파일명을 file 객체로 얻기
		File file = new File(ATTACHED_DIR + File.separator + fileName);
		
		if (file.exists()) {
			file.delete();
		}
	}
	
	// 검색 + 페이징 처리로직 공통
	public static Pagination getParameter(HttpServletRequest request) {
		Pagination pg = new Pagination();
		
		String pn = request.getParameter("pageNum");
		int pageNum = 1;
		if (pn != null) pageNum = Integer.parseInt(pn);
		
		pg.setPageNum(pageNum);

		Map<String, String> map = new HashMap<>();
		Enumeration<String> em = request.getParameterNames();
		while (em.hasMoreElements()) {
			String key = em.nextElement();
			String val = request.getParameter(key);
			
			if ("pageNum".equalsIgnoreCase(key)) continue;
			if (val.isBlank()) continue;
			
			map.put(key, val);
		}
		
		pg.setSearchMap(map);
		
		return pg;
	}
	
	// 임시 비밀번호 생성로직
	private static final char[] rndAllCharacters = new char[]{
		//number
		'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
		//uppercase
		'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
		'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
		//lowercase
		'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
		'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
		//special symbols
		'@', '$', '!', '%', '*', '?', '&'
	};

	public static String getRandomPassword(int length) {
		SecureRandom random = new SecureRandom();
		StringBuilder stringBuilder = new StringBuilder();

		int rndAllCharactersLength = rndAllCharacters.length;
		for (int i = 0; i < length; i++) {
			stringBuilder.append(rndAllCharacters[random.nextInt(rndAllCharactersLength)]);
		}

		return stringBuilder.toString();
	}
	
}
