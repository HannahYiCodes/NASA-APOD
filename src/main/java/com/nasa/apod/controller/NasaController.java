package com.nasa.apod.controller;

        import com.nasa.apod.models.NasaModel;
        import org.apache.coyote.Response;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.core.env.Environment;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.*;
        import org.springframework.web.client.HttpClientErrorException;
        import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/nasa")
public class NasaController {

    @Autowired
    private Environment env;
    private String nasaApodEndpoint = "https://api.nasa.gov/planetary/apod?api_key=";

    @GetMapping("/apod")
    public ResponseEntity<?> apodRoute(RestTemplate restTemplate) {
        try {
            String key = env.getProperty("APOD_KEY", "DEMO_KEY");
            String url = nasaApodEndpoint + key;
            NasaModel response = restTemplate.getForObject(url, NasaModel.class);
            return ResponseEntity.ok(response);

        } catch(HttpClientErrorException.Forbidden e) {
            String errorMsg = "Server has no API key or the API key is invalid. Please check the server code or contact support.";
            return ResponseEntity.status(500).body(errorMsg);
        } catch (Exception e) {
            System.out.println(e.getClass());
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    // APOD using PathVariables
    // http://localhost:4545/api/nasa/bydate/2022-09-07
    @GetMapping("/bydate/{date}")
    ResponseEntity<?> apodByDatePathVar (RestTemplate restTemplate, @PathVariable String date) {
        try {
            String key = env.getProperty("APOD_KEY");

            if (key == null) {
                return ResponseEntity.internalServerError().body("Server Error: api key not present");
            }

            String url = nasaApodEndpoint + key + "&date=" + date;
            NasaModel response = restTemplate.getForObject(url, NasaModel.class);
            return ResponseEntity.ok(response);

        } catch (HttpClientErrorException.BadRequest e) {

            String rawErr = e.getMessage() != null ? e.getMessage() : "";
            String apiErrMsg = extractNasaErrorMsg(rawErr);

            return ResponseEntity.badRequest().body("Invalid Date Provided " + date + "\n" + apiErrMsg);

        } catch(HttpClientErrorException.Forbidden e) {
            String errorMsg = "Server has no API key or the API key is invalid. Please check the server code or contact support.";
            return ResponseEntity.status(500).body(errorMsg);
        } catch (Exception e) {
        System.out.println(e.getClass());
        System.out.println(e.getMessage());
        return ResponseEntity.internalServerError().body(e.getMessage());
    }
    }


    // http://localhost:4545/api/nasa/bydate?date=2022-08-10
    @GetMapping("/bydate")
    ResponseEntity<?> apodByDateReqParam (RestTemplate restTemplate, @RequestParam () String date) {
        try {
        String key = env.getProperty("APOD_KEY");

        if (key == null) {
            return ResponseEntity.internalServerError().body("Server Error: api key not present");
        }

        String url = nasaApodEndpoint + key + "&date=" + date;
        NasaModel response = restTemplate.getForObject(url, NasaModel.class);
        return ResponseEntity.ok(response);

    } catch (HttpClientErrorException.BadRequest e) {

            String rawErr = e.getMessage() != null ? e.getMessage() : "";
            String apiErrMsg = extractNasaErrorMsg(rawErr);

            return ResponseEntity.badRequest().body("Invalid Date Provided " + date + "\n" + apiErrMsg);

        } catch(HttpClientErrorException.Forbidden e) {
            String errorMsg = "Server has no API key or the API key is invalid. Please check the server code or contact support.";
            return ResponseEntity.status(500).body(errorMsg);
        } catch (Exception e) {
            System.out.println(e.getClass());
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    private String extractNasaErrorMsg(String fullErrMsg) {
        String[] splitErrMsg = fullErrMsg.split("\"");
        for (int i = 0; i < splitErrMsg.length; i++) {
            if (splitErrMsg[i].equals("msg") && i+2 < splitErrMsg.length) {
                return splitErrMsg[i+2];
            }
        }
        return "Error: no more info available.";
    }
}

// DISPLAY IMAGE
//@GetMapping("/imagetest")
//    public class MyCanvas extends Canvas{
//        public void paint(Graphics g) {
//            Toolkit t = Toolkit.getDefaultToolkit();
//            Image i = t.getImage("image.png");
//            g.drawImage(i, 120,100,this);
//        }
//    }