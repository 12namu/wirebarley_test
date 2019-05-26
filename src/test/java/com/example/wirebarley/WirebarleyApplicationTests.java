package com.example.wirebarley;

import com.example.wirebarley.dto.WireInfo;
import org.hamcrest.core.IsNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WirebarleyApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void 환율정보조회() {
		ResponseEntity<WireInfo> response = restTemplate.getForEntity("http://apilayer.net/api/live?access_key=808afadff733f5dc7b5facc0416671ae", WireInfo.class);
		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
        assertThat(response.getBody(), IsNull.notNullValue());
	}



}
