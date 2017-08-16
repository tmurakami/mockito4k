#!/bin/sh

for test_kotlin_version in '1.0.7' '1.1.4'; do
    for test_mockito_version in '2.7.2' '2.8.47' 'latest.release'; do
        for test_mockito_type in 'core' 'inline'; do
            cmd="./gradlew -PtestKotlinVersion=${test_kotlin_version} -PtestMockitoVersion=${test_mockito_version} -PtestMockitoType=${test_mockito_type} clean test"
            echo ${cmd}
            eval ${cmd} || exit $?
        done
    done
done
