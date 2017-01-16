CREATE TABLE `bollywood_dev`.`movies` (
  `id`        INT           NOT NULL AUTO_INCREMENT,
  `version`   INT           NOT NULL    DEFAULT 0,
  `title`     VARCHAR(255)  NOT NULL,
  `watched`   BOOLEAN       NULL        DEFAULT FALSE,
  `rating`    VARCHAR(5)    NULL,
  `released`  DATE          NULL,
  `length`    INT           NULL,
  `created`   TIMESTAMP     NULL        DEFAULT now(),
  `modified`   TIMESTAMP     NULL        DEFAULT now(),
  PRIMARY KEY (`id`));
