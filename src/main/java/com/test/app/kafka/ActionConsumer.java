/*
 * Copyright 2012-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.test.app.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.app.action.QuoteActionHandler;
import com.test.app.action.QuoteActionFactory;
import com.test.app.dao.QuoteDao;
import com.test.app.dto.Quote;
import com.test.app.dto.QuoteActionModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
@Component
class ActionConsumer {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private QuoteActionFactory quoteActionFactory;

    @Autowired
    private QuoteDao quoteDao;

    @Autowired
    private EventProducer EventProducer;

	private static Logger logger = LoggerFactory.getLogger(ActionConsumer.class);

	@KafkaListener(topics = "${cloudkarafka.topic.action}")
	public void processMessage(String message,
							   @Header(KafkaHeaders.RECEIVED_PARTITION_ID) List<Integer> partitions,
							   @Header(KafkaHeaders.RECEIVED_TOPIC) List<String> topics,
							   @Header(KafkaHeaders.OFFSET) List<Long> offsets) throws IOException {

        QuoteActionModel quoteActionModel = objectMapper.readValue(message, QuoteActionModel.class);

        Quote quote = quoteDao.getQuote(quoteActionModel.getQuoteName());

        if(quote == null) {
            logger.info("Quote not found: " + quoteActionModel.getQuoteName());
        }

        QuoteActionHandler quoteActionHandler =quoteActionFactory.getQuoteAction(quote, quoteActionModel);

        Quote modifiedQuote = quoteActionHandler.handle();

        if(modifiedQuote != null) {
            quoteDao.createOrUpdateQuote(modifiedQuote);

        }

        EventProducer.send(modifiedQuote);
	}
}
