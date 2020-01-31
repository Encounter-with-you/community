package life.majiang.community.cache;

import life.majiang.community.dto.TagDTO;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TagCache {
    public static List<TagDTO> get() {
        List<TagDTO> tagDTOS = new ArrayList<>();
        TagDTO program = new TagDTO();
        program.setCategoryName("前端");
        program.setTags(Arrays.asList("javascript", "vue.js", "css", "html", "html5", "node.js", "react.js", "jquery", "css3", "es6", "typescript", "chrome", "npm", "bootstrap", "sass", "less", "chrome-devtools", "firefox", "angular", "coffeescript", "safari", "postcss", "postman", "fiddler", "webkit", "yarn", "firebug", "edge"));
        tagDTOS.add(program);

        TagDTO framework = new TagDTO();
        framework.setCategoryName("后端");
        framework.setTags(Arrays.asList("php", "java", "node.js", "python", "c++", "c", "golang", "spring", "django", "springboot", "flask", "后端", "c#", "swoole", "ruby", "asp.net", "ruby-on-railss", "cala", "rust", "lavarel", "爬虫"));
        tagDTOS.add(framework);

        return tagDTOS;
    }

    public static String filterInvalid(String tags) {
        String[] split = StringUtils.split(tags, ",");
        List<TagDTO> tagDTOS = get();
        List<String> tagList = tagDTOS.stream().flatMap(tag -> tag.getTags().stream()).collect(Collectors.toList());
        String invalid = Arrays.stream(split).filter(t -> !tagList.contains(t)).collect(Collectors.joining(","));
        return invalid;
    }
}
