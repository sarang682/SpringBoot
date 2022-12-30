package chap6.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import chap6.model.MemberVO;


@Mapper
public interface MemberDAO {
	
	public List<MemberVO> listMembers();
	
	public int addMember(MemberVO member);
	
	public int updateMember(MemberVO member) ;
	
	public int deleteMember(String id);
	
	public MemberVO getMember(String id);
	
	public MemberVO getMemberByEmail(String email);
	
}
