package com.examplekicklaandwebsite.KickLaand.repository;

import com.examplekicklaandwebsite.KickLaand.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import com.examplekicklaandwebsite.KickLaand.model.ForgotPassword;
import org.springframework.data.jpa.repository.Query;

public interface ForgotPasswordRepository extends JpaRepository<ForgotPassword, Integer> {

    @Query("select fp from ForgotPassword fp where fp.otp = ?1 and fp.user = ?2")
	Optional<ForgotPassword> findByOtpandUser(Integer Otp, UserAccount user);

    void deleteByUser(UserAccount user);
	
}
