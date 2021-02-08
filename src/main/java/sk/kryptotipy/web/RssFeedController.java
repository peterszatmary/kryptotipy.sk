package sk.kryptotipy.web;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.View;
import sk.kryptotipy.web.view.RssFeedView;

@AllArgsConstructor
@RestController
public class RssFeedController {

    private final RssFeedView view;

    @GetMapping("/rss")
    public View getFeed() {
        return view;
    }
}