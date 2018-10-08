bootstrap: submodules

submodules:
	git submodule update --init --recursive

build:
	./gradlew assembleDebug

.PHONY: build
