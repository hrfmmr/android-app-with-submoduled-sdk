bootstrap: submodules

submodules:
	git submodule update --init --recursive

build:
	./gradlew assembleDebug

test:
	./gradlew :app:testDebugUnitTest

.PHONY: build test
