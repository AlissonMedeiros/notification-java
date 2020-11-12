CREATE SEQUENCE hibernate_sequence START 1;

CREATE TABLE message_entity (
   message_id SERIAL PRIMARY KEY,
   message_body VARCHAR(64) NOT NULL,
   message_channel VARCHAR(64) NOT NULL,
   message_recipient_name VARCHAR(64) NOT NULL,
   message_recipient_email VARCHAR(64) NOT NULL,
   message_recipient_phone_number VARCHAR(64) NOT NULL,
   message_recipient_phone_id VARCHAR(64) NOT NULL,
   message_schedule_date TIMESTAMP NOT NULL
);

CREATE TABLE chat_entity (
   chat_id SERIAL PRIMARY KEY,
   chat_status VARCHAR(64) NOT NULL,
   chat_date TIMESTAMP NOT NULL,
   message_id integer NOT NULL,
   FOREIGN KEY (message_id)
         REFERENCES message_entity (message_id)
);
