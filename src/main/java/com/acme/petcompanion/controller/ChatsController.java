package com.acme.petcompanion.controller;

import com.acme.petcompanion.domain.model.Chat;
import com.acme.petcompanion.domain.service.ChatService;
import com.acme.petcompanion.resource.ChatResource;
import com.acme.petcompanion.resource.SaveChatResource;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ChatsController {

}