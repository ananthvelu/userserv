package com.review.userreview.api.dozer;


import com.review.userreview.domain.datamodel.dto.UserDAO;
import com.review.userreview.model.User;
import org.dozer.DozerBeanMapper;
import org.dozer.loader.DozerBuilder;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DTOToResponseMapper {
        /**
         * Defines a dtoToResponseMapper spring bean
         *
         * @return a dozer mapper
         */
        @Bean (name = "dtoToResponseMapper")
        public DozerBeanMapper dtoToResponseMapper() {
                DozerBeanMapper beanMapper = new DozerBeanMapper();
                beanMapper.addMapping(userToDTOMapping());
                return beanMapper;
        }

        @Bean (name = "userToDTOResponseMapper")
        public DozerBeanMapper userToDTOResponseMapper() {
                DozerBeanMapper beanMapper = new DozerBeanMapper();
                beanMapper.addMapping(userDTOToUserMapping());
                return beanMapper;
        }

        private BeanMappingBuilder userToDTOMapping() {
                return new ExtendedBeanMappingBuilder() {
                        @Override
                        protected void configure(DozerBuilder dozerBuilder) {
                                mapping(UserDAO.class, User.class);

                        }
                };
        }

        private BeanMappingBuilder userDTOToUserMapping() {
                return new ExtendedBeanMappingBuilder() {
                        @Override
                        protected void configure(DozerBuilder dozerBuilder) {
                                mapping(User.class, UserDAO.class);

                        }
                };
        }

}
