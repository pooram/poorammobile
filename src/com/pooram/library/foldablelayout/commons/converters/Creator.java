package com.pooram.library.foldablelayout.commons.converters;

public interface Creator<IN, OUT> {

	OUT create(IN obj);

}
