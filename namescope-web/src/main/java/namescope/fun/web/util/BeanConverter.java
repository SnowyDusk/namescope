package namescope.fun.web.util;

import namescope.fun.namer.context.NameParam;
import namescope.fun.web.request.NameRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

@Mapper(componentModel = "Spring")
@Service
public interface BeanConverter {
    BeanConverter INSTANCE = Mappers.getMapper(BeanConverter.class);

    NameParam convert(NameRequest nameRequest);
}
