package com.study.jpastudy.library.loan.presentation.dto;

import com.study.jpastudy.library.category.domain.Category;
import com.study.jpastudy.library.loan.application.dto.ReadLoanWithMemberDto;
import com.study.jpastudy.library.member.domain.Address;

import java.util.List;

public record LoanWithMemberResponse(MemberInfoDto memberInfo,
                                     List<LoanResponse> loanHistory) {

    public static LoanWithMemberResponse from(ReadLoanWithMemberDto readLoanWithMemberDto) {
        final MemberInfoDto memberInfoDto = MemberInfoDto.from(readLoanWithMemberDto.member());
        final List<LoanResponse> loans = readLoanWithMemberDto.loanDtoList().stream().map(LoanResponse::from).toList();

        return new LoanWithMemberResponse(memberInfoDto, loans);
    }

    public record MemberInfoDto(Long id, String name, Address address) {

        public static MemberInfoDto from(final ReadLoanWithMemberDto.MemberDto memberDto) {
            return new MemberInfoDto(memberDto.id(), memberDto.name(), memberDto.address());
        }
    }

    public record LoanResponse(Long id, Long bookId, String bookISBN,
                               String bookTitle, String author,
                               Category category, boolean isReturned) {

        public static LoanResponse from(final ReadLoanWithMemberDto.LoanDto loanDto) {
            return new LoanResponse(
                    loanDto.id(),
                    loanDto.book().getId(),
                    loanDto.book().getISBN(),
                    loanDto.book().getTitle(),
                    loanDto.book().getAuthor(),
                    loanDto.book().getCategory(),
                    loanDto.isReturned()
            );
        }
    }
}
