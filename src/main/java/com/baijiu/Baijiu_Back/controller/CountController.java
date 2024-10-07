package com.baijiu.Baijiu_Back.controller;

import com.baijiu.Baijiu_Back.common.Result;
import com.baijiu.Baijiu_Back.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@CrossOrigin
@RequestMapping("/count")
public class CountController {

    @Autowired
    private UsersService usersService;
    @Autowired
    private PoemsbylocationService poemsbylocationService;
    @Autowired
    private PoemsbydynastyService poemsbydynastyService;
    @Autowired
    private VesselService vesselService;
    @Autowired
    private PoemimagesService poemimagesService;
    @GetMapping("/api/gettotal")
    public Result getStats() {
        HashMap<String, Object> stats = new HashMap<>();
        Long poemsbydynastyCount =poemsbydynastyService.countAll();
        Long vesselCount =vesselService.countAll();
        Long poemsbylocationCount =poemsbylocationService.countAll();
        Long poemimagesCount =poemimagesService.countAll();
        Long userCount = usersService.countAll();

        stats.put("poemimagesCount",poemimagesCount);
        stats.put("poemsbydynastyCount", poemsbydynastyCount);
        stats.put("poemsbylocationCount", poemsbylocationCount);
        stats.put("userCount", userCount);
        stats.put("vesselCount", vesselCount);
        return Result.success(stats);
    }
}
