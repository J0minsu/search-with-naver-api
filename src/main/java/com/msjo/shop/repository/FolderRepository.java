package com.msjo.shop.repository;

import com.msjo.shop.entity.Folder;
import com.msjo.shop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FolderRepository extends JpaRepository<Folder, Long> {
    List<Folder> findAllByUser(User user);
    List<Folder> findAllByUserAndNameIn(User user, List<String> folderNames);
}