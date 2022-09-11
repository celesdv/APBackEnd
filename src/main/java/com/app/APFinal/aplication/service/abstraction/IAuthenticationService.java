package com.app.APFinal.aplication.service.abstraction;

import com.app.APFinal.aplication.rest.request.AuthenticationRequest;
import com.app.APFinal.aplication.rest.response.AuthenticationResponse;

public interface IAuthenticationService {

  AuthenticationResponse login(AuthenticationRequest authenticationRequest);

}
