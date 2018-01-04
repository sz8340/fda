#!/bin/bash
set -e
ls -l x
ls -l y

echo "xxx"
exec "$@"
echo "xxx"
