#!/bin/bash
# Bonus 1: Cron job script running every minute at second 23[cite: 4]
sleep 23
DIR="./monitored_dir"
MALICIOUS_DIR="./quarantine"
# Executes a single scan cycle similar to antivirusd.sh scan_directory