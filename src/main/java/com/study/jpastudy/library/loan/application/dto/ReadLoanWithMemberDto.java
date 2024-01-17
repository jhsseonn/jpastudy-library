package com.study.jpastudy.library.loan.application.dto;

import com.study.jpastudy.library.book.domain.Book;
import com.study.jpastudy.library.loan.domain.Loan;
import com.study.jpastudy.library.member.domain.Address;
import com.study.jpastudy.library.member.domain.Member;

import java.time.LocalDate;
import java.util.List;

public record ReadLoanWithMemberDto(MemberDto member, List<LoanDto> loanDtoList) {

    public static ReadLoanWithMemberDto of(final Member member, final List<Loan> loans) {
        final MemberDto memberDto = MemberDto.from(member);
        final List<LoanDto> loanDtos = loans.stream().map(LoanDto::from).toList();

        return new ReadLoanWithMemberDto(memberDto, loanDtos);
    }

    public record MemberDto(
            Long id, String name, Address address
    ) {

        public static MemberDto from(final Member member) {
            return new MemberDto(member.getId(), member.getName(), member.getAddress());
        }
    }

    public record LoanDto(Long id, Book book, LocalDate loanDate, LocalDate returnDate, boolean isReturned) {

        public static LoanDto from(final Loan loan) {
            return new LoanDto(loan.getId(), loan.getBook(), loan.getLoanDate(), loan.getReturnDate(), loan.isReturned());
        }
    }
}
