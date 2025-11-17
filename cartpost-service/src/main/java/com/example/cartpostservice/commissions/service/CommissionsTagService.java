package com.example.cartpostservice.commissions.service;

import com.example.cartpostservice.commissions.model.CommissionsTagEntity;
import com.example.cartpostservice.commissions.repository.CommissionsTagRepository;
import com.example.cartpostservice.commissions.service.dto.request.TagServiceCommand;
import com.example.cartpostservice.commissions.service.dto.response.TagServiceResult;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class CommissionsTagService implements CrudService<TagServiceCommand, TagServiceResult, String>{

    private CommissionsTagRepository commissionsTagRepository;

    @Override
    public String create(TagServiceCommand requestDto) {
        for(String tagCode : requestDto.tagCodes()){
            CommissionsTagEntity commissionsTagEntity = CommissionsTagEntity.builder()
                    .commissionCode(requestDto.commissionsCode())
                    .tagCode(tagCode).build();

            commissionsTagRepository.save(commissionsTagEntity);
        }

        return requestDto.commissionsCode();
    }

    @Override
    public TagServiceResult read(String commissionCode) {
        List<CommissionsTagEntity> tags = commissionsTagRepository.findByCommissionCode(commissionCode);

        List<String> tagCodes = tags.stream()
                .map(entity -> entity.getTagCode())
                .toList();


        TagServiceResult result = new TagServiceResult(
                commissionCode,
                tagCodes
        );

        return result;
    }

    @Override
    public void update(TagServiceCommand requestDto, String s) {

    }

    @Override
    public void delete(String s) {

    }

    @Override
    public boolean exist(String ownerCode, String targetCode) {
        return false;
    }
}
