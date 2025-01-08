package com.examplekicklaandwebsite.KickLaand.request;

import com.examplekicklaandwebsite.KickLaand.model.UserAccount;

public record CreateAccountRequest(UserAccount userAccount, Boolean signUpForNewsletter) {
}
