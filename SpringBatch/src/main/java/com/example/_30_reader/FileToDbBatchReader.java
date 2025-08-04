package com.example._30_reader;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

import com.example._70_dto.User;

@Configuration
public class FileToDbBatchReader {

	@Bean
	@StepScope
	@Value("#{jobParameters['filePath']}")  // get job parameter
	public FlatFileItemReader<User> ftdImportFileStepReader(String filePath) {
		return new FlatFileItemReaderBuilder<User>()
				.name("UserItemReader")
//				.resource(new ClassPathResource(filePath))
				.resource(new FileSystemResource(filePath))
				.delimited()
				.names(new String[]{"username", "password", "role", "enabled"})
				.targetType(User.class)
				.build();
	}
	
}
