package com.bok.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bok.blog.model.Board;
import com.bok.blog.model.User;
import com.bok.blog.repository.BoardRepository;



//스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해줌. IoC를 해준다.
@Service
public class BoardService {

	
		@Autowired
		private BoardRepository boardRepository;
		
	
		@Transactional
		public void 글쓰기(Board board,User user) {
			board.setCount(0);
			board.setUser(user);
			boardRepository.save(board);
		}
		
		@Transactional(readOnly = true)
		public Page<Board> 글목록(Pageable pageable){
			return boardRepository.findAll(pageable);
		}
		
		@Transactional(readOnly = true)
		public Board 글상세보기(int id) {
			return boardRepository.findById(id)
					.orElseThrow(( )->{
						return new IllegalArgumentException("글 상세보기 실패: 아이디를 찾을 수 없습니다.");
					});
		}
		
		@Transactional
		public void 글삭제하기(int id) {
			boardRepository.deleteById(id);
					}
		
		@Transactional
		public void 글수정하기(int id, Board requestBoard) {
			Board board = boardRepository.findById(id)
					.orElseThrow(()->{
						return new IllegalArgumentException("글 찾기 실패: 아이디를 찾을 수 없습니다.");
					});
			board.setTitle(requestBoard.getTitle());
			board.setContent(requestBoard.getContent());
		//해당 함수 종료시에(서비스가 종료될 때)트랜잭션이 종료된다. 이때 더티체킹 - 자동업데이트가 됨. flush
		}
}
