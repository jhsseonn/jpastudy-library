package com.study.jpastudy.library.loan.domain;

import com.study.jpastudy.library.book.domain.Book;
import com.study.jpastudy.library.member.domain.Member;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode
@ToString
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false, foreignKey = @ForeignKey(name = "loan_book"))
    private Book book;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false, foreignKey = @ForeignKey(name = "loan_member"))
    private Member member;

    private LocalDate loanDate =  LocalDate.now();

    private LocalDate returnDate = LocalDate.now().plusDays(7);

    private boolean isReturned = false;

    public Loan(final Book book, final Member member) {
        processLoan(book, member);
    }

    private void processLoan(final Book book, final Member member){
        this.book = book;
        this.member = member;
        book.updateIsLoaned();
        member.getLoanedBooks().add(this);
    }

    public void updateLoanDate(final LocalDate loanDate) {
        this.loanDate = loanDate;
        this.returnDate = loanDate.plusDays(7);
    }

    public void updateLoanStatus(){
        this.isReturned = true;
        book.updateIsReturned();
    }
}
