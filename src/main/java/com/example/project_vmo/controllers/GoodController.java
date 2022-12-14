package com.example.project_vmo.controllers;

import com.example.project_vmo.commons.filters.ValidImage;
import com.example.project_vmo.models.request.GoodDto;
import com.example.project_vmo.models.response.GoodResponse;
import com.example.project_vmo.models.response.MessageResponse;
import com.example.project_vmo.services.GoodService;
import com.example.project_vmo.services.ImageService;
import java.io.IOException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/good")
public class GoodController {

  @Autowired
  private GoodService goodService;
  @Autowired
  private ImageService imageService;

  @GetMapping
  public GoodResponse listGoods(
      @RequestParam(value = "pageNo", defaultValue = "0") int pageNo,
      @RequestParam(value = "pageSize", defaultValue = "5") int pageSize,
      @RequestParam(defaultValue = "goodsId",value = "sort") String name) {
    return goodService.getAllGoods(pageNo, pageSize,name);
  }

  @PostMapping()
  public ResponseEntity<?> createGoods(@RequestPart(value = "good") @Validated GoodDto goodDto,
      @RequestPart(value = "files", required = false) @Valid  MultipartFile[]  files)
      throws IOException {
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(goodService.createGood(goodDto, files));
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> updateGood(@RequestPart(value = "good")  GoodDto goodDto, @PathVariable int id,
      @RequestPart MultipartFile[] files,@AuthenticationPrincipal User user)
      throws IOException {
    return ResponseEntity.status(HttpStatus.ACCEPTED)
        .body(goodService.updateGood(goodDto, id, files,user));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteGood(@PathVariable int id,@AuthenticationPrincipal User user) {
    goodService.deleteGood(id,user);
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(new MessageResponse(HttpStatus.ACCEPTED.value(),"Good has been deleted"));
  }

  @GetMapping("/{goodId}")
  public ResponseEntity<?> getGoodById(@PathVariable("goodId") int goodId){
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(goodService.getGoodById(goodId));
  }
}
