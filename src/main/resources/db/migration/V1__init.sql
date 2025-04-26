CREATE TABLE customer_request (
                                  id BIGSERIAL PRIMARY KEY,
                                  customer_id BIGINT NOT NULL,
                                  status VARCHAR(20) NOT NULL,
                                  description TEXT,
                                  checked_by_company VARCHAR(50) NOT NULL,
                                  created_at TIMESTAMP DEFAULT now(),
                                  updated_at TIMESTAMP DEFAULT now()
);

CREATE TABLE supplier_offer (
                                id BIGSERIAL PRIMARY KEY,
                                supplier_id BIGINT NOT NULL,
                                request_id BIGINT NOT NULL REFERENCES customer_request(id),
                                status VARCHAR(20) NOT NULL,
                                inspection_score INT,
                                car_details TEXT,
                                price NUMERIC(10,2),
                                is_imported BOOLEAN,
                                created_at TIMESTAMP DEFAULT now(),
                                updated_at TIMESTAMP DEFAULT now(),
                                UNIQUE(supplier_id, request_id) -- Only one offer per supplier per request
);
