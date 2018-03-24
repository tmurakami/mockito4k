#!/bin/sh

for test_kotlin_version in '1.1.61' '1.2.31'; do
    for test_mockito_version in '2.7.0' '2.17.0' 'latest.release'; do
        for using_inline_mock_maker in false true; do
            echo '========================================='
            echo " Kotlin: ${test_kotlin_version}"
            echo " Mockito: ${test_mockito_version}"$(${using_inline_mock_maker} && echo ' with inline mock making')
            echo '========================================='
            ./gradlew --no-daemon clean test -PtestKotlinVersion=${test_kotlin_version} \
                                             -PtestMockitoVersion=${test_mockito_version} \
                                             $(${using_inline_mock_maker} && echo '-PusingInlineMockMaker') || exit $?
        done
    done
done
