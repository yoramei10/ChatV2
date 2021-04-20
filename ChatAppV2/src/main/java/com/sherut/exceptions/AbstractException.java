package com.sherut.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode()
@Data
@AllArgsConstructor
public class AbstractException extends RuntimeException{
    String message;
}
