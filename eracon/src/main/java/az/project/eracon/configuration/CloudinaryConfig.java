package az.project.eracon.configuration;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dqqmagpbo",
                "api_key", "523478894939669",
                "api_secret", "pguEn1MdpIA4ggZfu_jRjPvLqtE",
                "secure", true
        ));
    }
}
