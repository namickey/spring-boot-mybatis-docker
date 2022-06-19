DROP TABLE IF EXISTS public.item CASCADE;

CREATE TABLE public.item (
	id varchar NOT NULL,
	name varchar NULL,
	CONSTRAINT item_pk PRIMARY KEY (id)
);
