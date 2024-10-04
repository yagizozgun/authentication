package com.bidfood.bidFoodIntegration.auth.service;

import com.bidfood.bidFoodIntegration.auth.model.AuthRequest;
import com.bidfood.bidFoodIntegration.model.dto.CreateOrderResponse;
import com.bidfood.bidFoodIntegration.model.dto.CreateOutletResponse;
import com.bidfood.bidFoodIntegration.model.dto.CreateProductResponse;
import com.bidfood.bidFoodIntegration.dataAccess.OrderRepository;
import com.bidfood.bidFoodIntegration.dataAccess.OutletRepository;
import com.bidfood.bidFoodIntegration.dataAccess.ProductRepository;
import com.bidfood.bidFoodIntegration.model.entity.Order;
import com.bidfood.bidFoodIntegration.model.entity.Outlet;
import com.bidfood.bidFoodIntegration.model.entity.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@Component
public class AuthManager {

    // restClient sınıfı ile token almak
    @Autowired
    private RestClient.Builder restClient;
    @Autowired
    private OutletRepository outletRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderRepository orderRepository;

    public String getToken(String userName, String password)
    {
        AuthRequest authRequest = new AuthRequest(userName, password);
        return this.restClient.baseUrl("")
                .build()
                .post()
                .uri("/authorize")
                .body(authRequest)
                .retrieve()
                .body(String.class);
    }

    //RestTemplate
//    @Autowired
//    private RestTemplate restTemplate;
//
//    public String getToken2(String userName, String password) {
//        AuthRequest authRequest = new AuthRequest(userName, password);
//
//        // URL ve POST isteği için gerekli endpoint
//        String url = "";
//
//        // AuthRequest gövdesini içeren bir POST isteği gönderiyoruz
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON); // İsteğin JSON olduğunu belirtiyoruz
//
//        HttpEntity<AuthRequest> requestEntity = new HttpEntity<>(authRequest, headers);
//
//        // RestTemplate ile POST isteğini gönderiyoruz
//        ResponseEntity<String> response = restTemplate.postForEntity(url, requestEntity, String.class);
//
//        // Tokeni döndürüyoruz (Response body)
//        return response.getBody();
//    }

    public String getData()
    {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter myFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return restClient.baseUrl("")
                .build()
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("")
                        .queryParam("","")
                        .queryParam("", "")
                        .queryParam("", "")
                        .queryParam("", "")
                        .build())
                .header("Authorization", "Bearer " + "TEMPORARY TOKEN")
                .retrieve()
                .body(String.class);
    }
    @Scheduled(fixedRate = 60000)
    public ResponseEntity<String> saveData() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String fetchData = getData();
        JsonNode rootNode = mapper.readTree(fetchData);
        JsonNode dataNode = rootNode.get("data");

        if (dataNode==null)
        {
            return new ResponseEntity<>("Failed to fetch the data", HttpStatus.EXPECTATION_FAILED);
        }
        for (JsonNode orderNode : dataNode)
        {
            CreateOutletResponse createOutletResponse = mapper.treeToValue(orderNode, CreateOutletResponse.class);
            CreateOrderResponse createOrderResponse = mapper.treeToValue(orderNode, CreateOrderResponse.class);

            Outlet outlet = new Outlet();
            if (outletRepository.findByOutletCode(createOutletResponse.getOutletCode()) == null)
            {
                //save outlets to the database
                outlet.setOutletCode(createOutletResponse.getOutletCode());
                outlet.setSignName(createOutletResponse.getSignName());
                outlet.setLegalName(createOutletResponse.getLegalName());
                outlet.setGsmNumber(createOutletResponse.getGsmNumber());
                outlet.setEmail(createOutletResponse.getEmail());
                outlet.setAddress(createOutletResponse.getAddress());
                outlet.setCountry(createOutletResponse.getCountry());
                outlet.setCity(createOutletResponse.getCity());
                outlet.setPostCode(createOutletResponse.getPostCode());
                outlet.setLatitude(createOutletResponse.getLatitude());
                outlet.setLongitude(createOutletResponse.getLongitude());
                outlet.setTwStart(createOutletResponse.getTwStart());
                outlet.setTwFinish(createOutletResponse.getTwFinish());
                outlet.setServiceTime(createOutletResponse.getServiceTime());
                outletRepository.save(outlet);
            }


            JsonNode products = orderNode.get("products");
            for (JsonNode productNode : products)
            {
                CreateProductResponse createProductResponse = mapper.treeToValue(productNode, CreateProductResponse.class);

                // save products to the database
                Product product = new Product();
                if (productRepository.findByProductCode(createProductResponse.getProductCode()) == null)
                {
                    product.setProductCode(createProductResponse.getProductCode());
                    product.setProductName(createProductResponse.getProductName());
                    product.setQuantity(createProductResponse.getQuantity());
                    product.setSize1(createProductResponse.getSize1());
                    product.setSize2(createProductResponse.getSize2());
                    product.setSize3(createProductResponse.getSize3());
                    product.setSize4(createProductResponse.getSize4());
                    product.setQuantityUnit(createProductResponse.getQuantityUnit());
                    product.setOrderType(createProductResponse.getOrderType());
                    productRepository.save(product);
                }

                // create new orders in the database
                Order order = new Order();
                if (orderRepository.findByOrderCode(createOrderResponse.getOrderCode()) == null)
                {
                    order.setOrderCode(createOrderResponse.getOrderCode());
                    order.setProductUid(product.getUid());
                    order.setOutletUid(outlet.getUid());
                    order.setCargoWidth(createOrderResponse.getCargoWidth());
                    order.setCargoDepth(createOrderResponse.getCargoDepth());
                    order.setCargoWeight(createOrderResponse.getCargoWeight());
                    order.setCargoHeight(createOrderResponse.getCargoHeight());
                    order.setDeliveryDate(createOrderResponse.getDeliveryDate());
                    order.setOrderDate(createOrderResponse.getOrderDate());
                    orderRepository.save(order);
                }
            }
        }
        System.out.println("Veriler çekildi");
        return new ResponseEntity<String>("data added to the database successfully", HttpStatus.OK);
    }

}
