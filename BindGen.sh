#!/usr/bin/env bash
java -cp ../../lib:../../lib/jibx-tools.jar org.jibx.binding.generator.BindGen -t ../../src/main/resources -v ocean.example.netty.httpxml.domain.Order