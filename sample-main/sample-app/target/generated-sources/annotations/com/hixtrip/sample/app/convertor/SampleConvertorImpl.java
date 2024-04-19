package com.hixtrip.sample.app.convertor;

import com.hixtrip.sample.client.sample.vo.SampleVO;
import com.hixtrip.sample.domain.sample.model.Sample;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-18T20:16:56+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (JetBrains s.r.o.)"
)
public class SampleConvertorImpl implements SampleConvertor {

    @Override
    public SampleVO sampleToSampleVO(Sample sample) {
        if ( sample == null ) {
            return null;
        }

        SampleVO.SampleVOBuilder sampleVO = SampleVO.builder();

        if ( sample.getId() != null ) {
            sampleVO.id( String.valueOf( sample.getId() ) );
        }
        sampleVO.name( sample.getName() );

        return sampleVO.build();
    }
}
