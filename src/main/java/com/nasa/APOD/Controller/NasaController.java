package com.nasa.APOD.Controller;

        import com.fasterxml.jackson.databind.util.JSONPObject;
        import org.springframework.web.bind.annotation.*;
        import org.springframework.web.client.RestTemplate;

        import java.awt.*;
        import javax.swing.JFrame;

        import java.awt.*;
        import java.util.Date;

@RestController
@RequestMapping("/nasa")
public class NasaController {
    private String myNasaKey = "gu6DWzRFvzktFdRu2gN98jMxu6oJBlrZJfcodsDV";
    private String nasaApodEndpoint = "https://api.nasa.gov/planetary/apod?api_key=" + myNasaKey;

    @GetMapping("/")
    private String routeRoute() {
        return "Your requested root";
    }

    @GetMapping("/apod")
    public Object apodRoute(RestTemplate restTemplate) {
        return restTemplate.getForObject(nasaApodEndpoint, Object.class);
    }

//    public class MyCanvas extends Canvas{
//        public void paint(Graphics g) {
//            Toolkit t = Toolkit.getDefaultToolkit();
//            Image i = t.getImage("image.png");
//            g.drawImage(i, 120,100,this);
//        }
//    }

    //date
    //localhost:5000/v1/apod?api_key=DEMO_KEY&date=2014-10-01
}
// http://localhost:8080/apod