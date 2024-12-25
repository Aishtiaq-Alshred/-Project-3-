package com.example.project3.Controller;


import com.example.project3.ApiResponce.ApiResponse;
import com.example.project3.Model.Account;
import com.example.project3.Model.User;
import com.example.project3.Service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;


    @GetMapping("/get-my")
    public ResponseEntity getMyAccount(@AuthenticationPrincipal User user){
        return ResponseEntity.status(200).body(accountService.getMyAccount(user.getId()));
    }

    @GetMapping("/get-all")
    public ResponseEntity getAll(){
        return ResponseEntity.status(200).body(accountService.getAllAccount());
    }


    @PostMapping("/add")
    public ResponseEntity addAccount(@AuthenticationPrincipal User user, @RequestBody @Valid Account account){
        accountService.addAccount(user.getId(),account);
        return ResponseEntity.status(200).body(new ApiResponse("Account Added ,"+user.getUsername()));
    }


    @PutMapping("/update/{account_id}")
    public ResponseEntity updateAccount(@AuthenticationPrincipal User user, @PathVariable Integer account_id , @RequestBody @Valid Account account){
        accountService.updateAccount(user.getId(),account_id,account);
        return ResponseEntity.status(200).body(new ApiResponse("Account updated ,"+user.getUsername()));
    }


    @DeleteMapping("/delete/{account_id}")
    public ResponseEntity deleteAccount(@AuthenticationPrincipal User user,@PathVariable Integer account_id){
        accountService.deleteAccount(user.getId(),account_id);
        return ResponseEntity.status(200).body(new ApiResponse("Account deleted ,"+user.getUsername()));
    }



    @PutMapping("/activ/{account_id}")
    public ResponseEntity active(@AuthenticationPrincipal User user,@PathVariable Integer account_id ){
        accountService.activateAccount(user.getId(),account_id);
        return ResponseEntity.status(200).body(new ApiResponse("is Activated"));
    }


    @GetMapping("/details/{account_id}")
    public ResponseEntity viewAccountDetails(@AuthenticationPrincipal User user ,@PathVariable Integer account_id) {
        return ResponseEntity.ok(accountService.viewAccountDetails(user.getId(),account_id));
    }


    @PutMapping("/deposit/{account_id}/{amount}")
    public ResponseEntity depositMoney (@AuthenticationPrincipal User user, @PathVariable Integer account_id , @PathVariable double amount ){
        accountService.depositMoney(user.getId(),account_id,amount);
        return ResponseEntity.status(200).body(new ApiResponse("Deposit money successfully"));
    }


    @PutMapping("/withdraw/{account_id}/{amount}")
    public ResponseEntity withdrawMoney (@AuthenticationPrincipal User user, @PathVariable Integer account_id , @PathVariable double amount ){
        accountService.withdrawMoney(user.getId(),account_id,amount);
        return ResponseEntity.status(200).body(new ApiResponse("Withdraw money successfully"));
    }

    @PutMapping("/transferFunds/{fromAccount_id}/{toAccount_id}/{amount}")
    public ResponseEntity transferFunds (@AuthenticationPrincipal User user , @PathVariable Integer fromAccount_id, @PathVariable Integer toAccount_id, @PathVariable double amount) {
        accountService.transferFunds(user.getId(), fromAccount_id, toAccount_id, amount);
        return ResponseEntity.status(200).body(new ApiResponse("Transfer funds successfully"));

    }

    @PutMapping("/block-account/{account_id}")
    public ResponseEntity blockAccount(@AuthenticationPrincipal User user, @PathVariable Integer account_id){
        accountService.blockAccount(user.getId(),account_id);
        return ResponseEntity.status(200).body(new ApiResponse("Account blocked Successfully"));
    }
}
