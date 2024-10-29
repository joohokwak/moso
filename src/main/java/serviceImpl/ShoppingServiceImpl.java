package serviceImpl;

import java.util.List;

import common.Pagination;
import dao.ShoppingDAO;
import dto.ItemReviewDTO;
import dto.ShoppingDTO;
import service.ShoppingService;

public class ShoppingServiceImpl implements ShoppingService {
	private ShoppingDAO dao;
	
	public ShoppingServiceImpl() {
		dao = new ShoppingDAO();
	}
	
	// main
	@Override
	public List<ShoppingDTO> viewMain(String ty, String ordered, String id, Pagination pg) {
		return dao.viewMain(ty, ordered, id, pg);
	}
	
	// buy item
	@Override
	public ShoppingDTO buyMain(int num) {
		return dao.buyMain(num);
	}
	@Override
	public ShoppingDTO writeItem(int itemno) {
		return dao.writeItem(itemno);
	}

	// like
	@Override
	public int insertLike(int no, String id) {
		return dao.insertLike(no, id);
	}
	
	// image
	@Override
	public List<String> imageName(int num) {
		return dao.imageName(num);
	}
	
	// review
	@Override
	public List<ItemReviewDTO> reviewAll(int num, Pagination pg) {
		return dao.reviewAll(num, pg);
	}
	@Override
	public ItemReviewDTO reviewOne(int rvno) {
		return dao.reviewOne(rvno);
	}
	// review 추가/수정/삭제
	@Override
	public int rvwrite(ItemReviewDTO dto) {
		return dao.rvwrite(dto);
	}
	@Override
	public int rvupdate(ItemReviewDTO dto) {
		return dao.rvupdate(dto);
	}
	@Override
	public int rvdelete(int rvno) {
		return dao.rvdelete(rvno);
	}
	
	// Q&A
	@Override
	public List<ItemReviewDTO> qnaAll(int num, Pagination pg) {
		return dao.qnaAll(num, pg);
	}
	@Override
	public ItemReviewDTO qnaOne(int qnano) {
		return dao.qnaOne(qnano);
	}
	// Q&A 추가/수정/삭제
	// 타입지정은 개발 시 경로, 대처, 조건을 위해 필요
	@Override
	public boolean qnaCreate(ItemReviewDTO qnaCre) {
		return dao.qnaCreate(qnaCre) > 0;
	}
	@Override
	public int qnaDel(int no) {
		return dao.qnaDel(no);
	}
	@Override
	public boolean qnaUpdate(ItemReviewDTO qnaUp) {
		return dao.qnaUpdate(qnaUp) > 0;
	}
	// Q&A 답변
	@Override
	public int ansCreate(int no, String ans) {
		return dao.ansCreate(no, ans);
	}
}
