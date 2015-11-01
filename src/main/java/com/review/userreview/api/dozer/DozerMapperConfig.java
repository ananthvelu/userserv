//package com.review.userreview.api.dozer;
//
//
//import org.dozer.DozerBeanMapper;
//import org.dozer.loader.DozerBuilder;
//import org.dozer.loader.DozerBuilder.ConfigurationBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//public class DozerMapperConfig {
//    /**
//     * Defines a RequestToDTOMapper spring bean
//     *
//     * @return a dozer mapper
//     */
//    @Bean
//    public DozerBeanMapper domainDataMapper() {
//        DozerBeanMapper beanMapper = new DozerBeanMapper();
//        beanMapper.addMapping(new ExtendedBeanMappingBuilder() {
//            @Override
//            protected void configure(DozerBuilder builder) {
//                ConfigurationBuilder configuration = builder.configuration();
//                RequestToDTOMapper.userMapperConfig(configuration, this);
//            }
//        });
//        return beanMapper;
//    }
//}
