package com.hixtrip.sample.infra.db.convertor;

import com.hixtrip.sample.domain.sample.model.Sample;
import com.hixtrip.sample.infra.db.dataobject.SampleDO;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-18T20:16:53+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (JetBrains s.r.o.)"
)
public class SampleDOConvertorImpl implements SampleDOConvertor {

    @Override
    public Sample doToDomain(SampleDO sampleDO) {
        if ( sampleDO == null ) {
            return null;
        }

        Sample.SampleBuilder<?, ?> sample = Sample.builder();

        sample.id( sampleDO.getId() );
        sample.name( sampleDO.getName() );

        return sample.build();
    }
}
