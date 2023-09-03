package net.test.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.test.Entities.File;

@Repository
public interface FileUploadRepository extends JpaRepository<File,Integer> {

}
