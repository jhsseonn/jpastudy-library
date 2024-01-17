package com.study.jpastudy.library.loan.presentation;

import com.study.jpastudy.library.loan.application.LoanService;
import com.study.jpastudy.library.loan.application.dto.ReadLoanWithMemberDto;
import com.study.jpastudy.library.loan.presentation.dto.LoanWithMemberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/loan")
@RequiredArgsConstructor
public class LoanController {

    private final LoanService loanService;

    @PostMapping("/{memberId}/{bookId}")
    public ResponseEntity<Void> requestLoan(@PathVariable(name = "memberId") final Long memberId,
                                            @PathVariable(name = "bookId") final Long bookId) {
        loanService.requestLoan(memberId, bookId);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/history/{memberId}")
    public ResponseEntity<LoanWithMemberResponse> readAllWithMember(@PathVariable(name = "memberId") final Long memberId,
                                                                    @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
                                                                    @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
                                                                    @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy) {
        final ReadLoanWithMemberDto readLoanWithMemberDto = loanService.readAllWithMember(memberId, pageNo, pageSize, sortBy);
        final LoanWithMemberResponse response = LoanWithMemberResponse.from(readLoanWithMemberDto);

        return ResponseEntity.ok(response);
    }
}
