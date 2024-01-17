package com.study.jpastudy.library.loan.infrastructure;

import com.study.jpastudy.library.loan.domain.Loan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Long> {

    @Query("""
            SELECT l
            FROM Loan l
            JOIN FETCH l.book
            JOIN FETCH l.member lm
            WHERE lm.id = :memberId
            ORDER BY l.loanDate
            """)
    Page<Loan> findAllByMemberId(Long memberId, Pageable pageable);

    @Query("""
            SELECT l
            FROM Loan l
            JOIN FETCH l.book
            JOIN FETCH l.member lm
            WHERE lm.id = :memberId AND l.isReturned = false
            ORDER BY l.loanDate
            """)
    List<Loan> findLoanedByMemberId(Long memberId);
}
