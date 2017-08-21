#!/bin/sh

for test_kotlin_version in '1.0.7' '1.1.4-2'; do
    for test_mockito_version in '2.7.0' '2.7.22' '2.8.55' 'latest.release'; do
        for options in '' '-PusingInlineMockMaker'; do
            cmd=`echo "./gradlew clean test -PtestKotlinVersion=${test_kotlin_version} -PtestMockitoVersion=${test_mockito_version} ${options}" | sed -e 's/[[:space:]]*$//'`
            echo "\n> ${cmd}"
            eval ${cmd} || exit $?
        done
    done
done
