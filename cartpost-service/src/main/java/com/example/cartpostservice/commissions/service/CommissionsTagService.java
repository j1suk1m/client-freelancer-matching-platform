package com.example.cartpostservice.commissions.service;

import com.example.cartpostservice.commissions.model.CommissionsTagEntity;
import com.example.cartpostservice.commissions.repository.CommissionsTagRepository;
import com.example.cartpostservice.commissions.service.dto.request.TagSaveCommand;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class CommissionsTagService implements CrudService<TagSaveCommand, String>{

    private CommissionsTagRepository commissionsTagRepository;

    @Override
    public String create(TagSaveCommand requestDto) {
        for(String tagCode : requestDto.tagCodes()){
            CommissionsTagEntity commissionsTagEntity = CommissionsTagEntity.builder()
                    .commissionCode(requestDto.commissionsCode())
                    .tagCode(tagCode).build();

            commissionsTagRepository.save(commissionsTagEntity);
        }

        return requestDto.commissionsCode();
    }

    @Override
    public Optional<TagSaveCommand> read(String s) {
        return Optional.empty();
    }

    @Override
    public void update(TagSaveCommand requestDto, String s) {

    }

    @Override
    public void delete(String s) {

    }

    @Override
    public boolean exist(String ownerCode, String targetCode) {
        return false;
    }
}
