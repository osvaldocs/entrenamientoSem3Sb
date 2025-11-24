package com.riwi.H3.infrastructure.mapper;

import com.riwi.H3.domain.model.Event;
import com.riwi.H3.infrastructure.entity.EventEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { VenueMapper.class })
public interface EventMapper {
    EventEntity toEntity(Event model);
    Event toModel(EventEntity entity);
}
