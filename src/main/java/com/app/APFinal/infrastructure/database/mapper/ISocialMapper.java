package com.app.APFinal.infrastructure.database.mapper;

import com.app.APFinal.aplication.rest.request.SocialRequest;
import com.app.APFinal.aplication.rest.response.SocialResponse;
import com.app.APFinal.infrastructure.database.entity.Social;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ISocialMapper {

  SocialResponse toSocialResponse(Social social);

  Social toSocial(SocialRequest request);

  List<SocialResponse> toListSocialResponse(List<Social> socialList);

}
