package com.msjo.shop.service;

import com.msjo.shop.dto.res.FolderResponseDto;
import com.msjo.shop.entity.Folder;
import com.msjo.shop.entity.User;
import com.msjo.shop.repository.FolderRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
@Transactional
public class FolderService {

    FolderRepository folderRepository;

    // 로그인한 회원에 폴더들 등록
    public void addFolders(List<String> folderNames, User user) {

        // 입력으로 들어온 폴더 이름을 기준으로, 회원이 이미 생성한 폴더들을 조회합니다.
        List<Folder> existFolderList = folderRepository.findAllByUserAndNameIn(user, folderNames);

        List<Folder> folderList = new ArrayList<>();

        for (String folderName : folderNames) {
            // 이미 생성한 폴더가 아닌 경우만 폴더 생성
            if (!isExistFolderName(folderName, existFolderList)) {
                Folder folder = new Folder(folderName, user);
                folderList.add(folder);
            } else {
                throw new IllegalArgumentException("폴더명이 중복되었습니다.");
            }
        }

        folderRepository.saveAll(folderList);
    }

    // 로그인한 회원이 등록된 모든 폴더 조회
    public List<FolderResponseDto> getFolders(User user) {
        List<Folder> folderList = folderRepository.findAllByUser(user);
        List<FolderResponseDto> responseDtoList = new ArrayList<>();

        for (Folder folder : folderList) {
            responseDtoList.add(new FolderResponseDto(folder));
        }

        return responseDtoList;
    }

    private Boolean isExistFolderName(String folderName, List<Folder> existFolderList) {
        // 기존 폴더 리스트에서 folder name 이 있는지?
        for (Folder existFolder : existFolderList) {
            if(folderName.equals(existFolder.getName())) {
                return true;
            }
        }
        return false;
    }

}
