package com.riwi.H3.infrastructure.mapper;

import com.riwi.H3.domain.model.Venue;
import com.riwi.H3.infrastructure.entity.VenueEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VenueMapper {

    VenueEntity toEntity(Venue model);

    Venue toModel(VenueEntity entity);
}
