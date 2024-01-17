package com.study.jpastudy.library.loan.application;

import com.study.jpastudy.library.book.application.exception.NotFoundBookException;
import com.study.jpastudy.library.book.domain.Book;
import com.study.jpastudy.library.book.infrastructure.BookRepository;
import com.study.jpastudy.library.loan.application.dto.ReadLoanWithMemberDto;
import com.study.jpastudy.library.loan.domain.Loan;
import com.study.jpastudy.library.loan.infrastructure.LoanRepository;
import com.study.jpastudy.library.member.application.exception.InvalidMemberToLoan;
import com.study.jpastudy.library.member.application.exception.NotFoundMemberException;
import com.study.jpastudy.library.member.domain.Member;
import com.study.jpastudy.library.member.infrastructure.MemberRepository;
import com.study.jpastudy.library.reservation.domain.Reservation;
import com.study.jpastudy.library.reservation.infrastructure.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class LoanService {

    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;
    private final LoanRepository loanRepository;
    private final ReservationRepository reservationRepository;

    public void requestLoan(final Long memberId, final Long bookId) {
        final Member member = validateMember(memberId);
        validateMemberToLoan(member);
        final Book book = bookRepository.findById(bookId)
                                        .orElseThrow(NotFoundBookException::new);
        validateBookToLoan(book, member);
        persistLoan(member, book);
    }

    private void persistLoan(final Member member, final Book book) {
        final Loan loan = new Loan(book, member);
        loanRepository.save(loan);
    }

    private Member validateMember(final Long memberId) {
        return memberRepository.findById(memberId)
                               .orElseThrow(NotFoundMemberException::new);
    }

    private void validateMemberToLoan(final Member member) {
        final List<Loan> loanedBooks = loanRepository.findLoanedByMemberId(member.getId());
        if (loanedBooks.size() >= 2) {
            throw new InvalidMemberToLoan.InvalidInvalidMemberToLoan();
        }
    }

    private void validateBookToLoan(final Book book, final Member member) {
        if (book.isLoaned()) {
            throw new InvalidMemberToLoan.InvalidInvalidMemberToLoan();
        }
        if (book.isReserved()) {
            final List<Reservation> reservations = reservationRepository.findAllByBookId(book.getId());
            if (!Objects.equals(reservations.get(0)
                                            .getMember()
                                            .getId(), member.getId())) {
                throw new InvalidMemberToLoan.InvalidInvalidMemberNotReserved();
            }
        }
    }

    public ReadLoanWithMemberDto readAllWithMember(final Long memberId, final int pageNo,
                                                   final int pageSize,
                                                   final String sortBy) {
        final Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy)
                                                                       .descending());
        final Member member = validateMember(memberId);
        final Page<Loan> loans = loanRepository.findAllByMemberId(member.getId(), pageable);

        return ReadLoanWithMemberDto.of(member, loans.getContent());
    }
}
