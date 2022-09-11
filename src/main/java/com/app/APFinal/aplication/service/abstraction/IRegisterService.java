package com.app.APFinal.aplication.service.abstraction;

import com.app.APFinal.aplication.rest.request.RegisterRequest;
import com.app.APFinal.aplication.rest.response.RegisterResponse;

public interface IRegisterService {

  RegisterResponse register(RegisterRequest registerRequest);

}
