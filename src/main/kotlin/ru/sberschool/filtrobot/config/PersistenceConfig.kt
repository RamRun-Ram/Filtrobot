package ru.sberschool.filtrobot.config

import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@EnableJpaRepositories(basePackages = ["ru.sberschool.filterbot.persistence.repository"])
class PersistenceConfig
