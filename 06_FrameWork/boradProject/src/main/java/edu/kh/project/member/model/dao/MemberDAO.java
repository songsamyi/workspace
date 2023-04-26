package edu.kh.project.member.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.kh.project.member.model.dto.Member;

@Repository // Persistence Layer, 영속성 관련 클래스
// 				(파일, DB관련 클래스) + Bean등록 (== spring리 만들어놓은 객체가 있음)
public class MemberDAO {
	
	
	// SqlSessionTemplate(마이바티스 객체) DI
	@Autowired // 등록된 Bean중에서 SqlSessionTemplate타입의 Bean을 주입
	private SqlSessionTemplate sqlSession;
	
	
	/** 로그인 DAO
	 * @param inputMember
	 * @return 회원 정보 또는 null
	 */
	public Member login(Member inputMember) {
		
		
		// 마이바티스를 이용해서 1행 조회(selecctOne)
		// sqlSession.selectOne("namespace값.id값", 전달할 값);
		// -> namespace가 일치하는 Mapper에서 id가 일치하는 SQL구문을 수행 후 결과를 1행(dto, 기본자료형)반환
		return sqlSession.selectOne("memberMapper.login", inputMember);
	}

	
}
